import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/entity'
import { EFkConstraint } from '../../core/entitydefine'
import {FuncType} from './functype'
import {Member} from './member'
import {RoadFuncRel} from './roadfuncrel'

@Entity("t_func_company",'tunnel')
export class FuncCompany extends BaseEntity{
	@Id()
	@Column({
		name:'func_company_id',
		type:'int',
		nullable:false
	})
	private funcCompanyId:number;

	@ManyToOne({entity:'FuncType',eager:false})
	@JoinColumn({name:'func_type_id',refName:'func_type_id'})
	private funcType:FuncType;

	@Column({
		name:'func_company_name',
		type:'string',
		nullable:true
	})
	private funcCompanyName:string;

	@Column({
		name:'tel',
		type:'string',
		nullable:true
	})
	private tel:string;

	@Column({
		name:'tel1',
		type:'string',
		nullable:true
	})
	private tel1:string;

	@Column({
		name:'tel2',
		type:'string',
		nullable:true
	})
	private tel2:string;

	@Column({
		name:'contact_man',
		type:'string',
		nullable:true
	})
	private contactMan:string;

	@Column({
		name:'address',
		type:'string',
		nullable:true
	})
	private address:string;

	@OneToMany({entity:'Member',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'funcCompany',eager:false})
	private members:Array<Member>;

	@OneToMany({entity:'RoadFuncRel',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'funcCompany',eager:false})
	private roadFuncRels:Array<RoadFuncRel>;

	public getFuncCompanyId():number{
		return this.funcCompanyId;
	}
	public setFuncCompanyId(value:number){
		this.funcCompanyId = value;
	}

	public getFuncType():FuncType{
		return this.funcType;
	}
	public setFuncType(value:FuncType){
		this.funcType = value;
	}

	public getFuncCompanyName():string{
		return this.funcCompanyName;
	}
	public setFuncCompanyName(value:string){
		this.funcCompanyName = value;
	}

	public getTel():string{
		return this.tel;
	}
	public setTel(value:string){
		this.tel = value;
	}

	public getTel1():string{
		return this.tel1;
	}
	public setTel1(value:string){
		this.tel1 = value;
	}

	public getTel2():string{
		return this.tel2;
	}
	public setTel2(value:string){
		this.tel2 = value;
	}

	public getContactMan():string{
		return this.contactMan;
	}
	public setContactMan(value:string){
		this.contactMan = value;
	}

	public getAddress():string{
		return this.address;
	}
	public setAddress(value:string){
		this.address = value;
	}

	public getMembers():Array<Member>{
		return this.members;
	}
	public setMembers(value:Array<Member>){
		this.members = value;
	}

	public getRoadFuncRels():Array<RoadFuncRel>{
		return this.roadFuncRels;
	}
	public setRoadFuncRels(value:Array<RoadFuncRel>){
		this.roadFuncRels = value;
	}

}