
import {FuncType} from './functype'
import {Member} from './member'
import {RoadFuncRel} from './roadfuncrel'
import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_func_company",'tunnel')
export class FuncCompany extends BaseEntity{
	@Id()
	@Column({
		name:'func_company_id',
		type:'int',
		nullable:false
	})
	funcCompanyId:number;

	@ManyToOne({entity:FuncCompany,lazyFetch:true})
	@JoinColumn({name:'func_type_id',refName:'func_type_id'})
	funcType:FuncType;

	@Column({
		name:'func_company_name',
		type:'string',
		nullable:true
	})
	funcCompanyName:string;

	@Column({
		name:'tel',
		type:'string',
		nullable:true
	})
	tel:string;

	@Column({
		name:'tel1',
		type:'string',
		nullable:true
	})
	tel1:string;

	@Column({
		name:'tel2',
		type:'string',
		nullable:true
	})
	tel2:string;

	@Column({
		name:'contact_man',
		type:'string',
		nullable:true
	})
	contactMan:string;

	@Column({
		name:'address',
		type:'string',
		nullable:true
	})
	address:string;

	@OneToMany({entity:'Member',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'funcCompany',lazyFetch:true})
	members:Array<Member>;

	@OneToMany({entity:'RoadFuncRel',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'funcCompany',lazyFetch:true})
	roadFuncRels:Array<RoadFuncRel>;

}