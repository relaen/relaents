import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/baseentity'
import { EFkConstraint } from '../../core/entitydefine'
import {Area} from './area'
import {Member} from './member'
import {Tunnel} from './tunnel'

@Entity("t_agent",'tunnel')
export class Agent extends BaseEntity{
	@Id({generator:'table',table:'t_id_table',keyName:'ID_AGENT'})
	@Column({
		name:'agent_id',
		type:'int',
		nullable:false
	})
	private agentId:number;

	@ManyToOne({entity:'Area',eager:true})
	@JoinColumn({name:'area_id',refName:'area_id'})
	private area:Area;

	@Column({
		name:'agent_name',
		type:'string',
		nullable:true
	})
	private agentName:string;

	@Column({
		name:'manager',
		type:'string',
		nullable:true
	})
	private manager:string;

	@Column({
		name:'manager_idno',
		type:'string',
		nullable:true
	})
	private managerIdno:string;

	@Column({
		name:'contactor',
		type:'string',
		nullable:true
	})
	private contactor:string;

	@Column({
		name:'manager_tel',
		type:'string',
		nullable:true
	})
	private managerTel:string;

	@Column({
		name:'contact_tel',
		type:'string',
		nullable:true
	})
	private contactTel:string;

	@Column({
		name:'address',
		type:'string',
		nullable:true
	})
	private address:string;

	@Column({
		name:'email',
		type:'string',
		nullable:true
	})
	private email:string;

	@Column({
		name:'zipcode',
		type:'string',
		nullable:true
	})
	private zipcode:string;

	@OneToMany({entity:'Member',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'agent',eager:false})
	private members:Array<Member>;

	@OneToMany({entity:'Tunnel',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'agent',eager:false})
	private tunnels:Array<Tunnel>;

	public getAgentId():number{
		return this.agentId;
	}
	public setAgentId(value:number){
		this.agentId = value;
	}

	public getArea():Area{
		return this.area;
	}
	public setArea(value:Area){
		this.area = value;
	}

	public getAgentName():string{
		return this.agentName;
	}
	public setAgentName(value:string){
		this.agentName = value;
	}

	public getManager():string{
		return this.manager;
	}
	public setManager(value:string){
		this.manager = value;
	}

	public getManagerIdno():string{
		return this.managerIdno;
	}
	public setManagerIdno(value:string){
		this.managerIdno = value;
	}

	public getContactor():string{
		return this.contactor;
	}
	public setContactor(value:string){
		this.contactor = value;
	}

	public getManagerTel():string{
		return this.managerTel;
	}
	public setManagerTel(value:string){
		this.managerTel = value;
	}

	public getContactTel():string{
		return this.contactTel;
	}
	public setContactTel(value:string){
		this.contactTel = value;
	}

	public getAddress():string{
		return this.address;
	}
	public setAddress(value:string){
		this.address = value;
	}

	public getEmail():string{
		return this.email;
	}
	public setEmail(value:string){
		this.email = value;
	}

	public getZipcode():string{
		return this.zipcode;
	}
	public setZipcode(value:string){
		this.zipcode = value;
	}

	public getMembers():Array<Member>{
		return this.members;
	}
	public setMembers(value:Array<Member>){
		this.members = value;
	}

	public getTunnels():Array<Tunnel>{
		return this.tunnels;
	}
	public setTunnels(value:Array<Tunnel>){
		this.tunnels = value;
	}

}